/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.groovy.ginq.provider.collection


import groovy.transform.CompileStatic

/**
 * Represents named record.
 * Given {@code p.name, t.manDay}, the {@code sourceRecord} stores the source record, which is record from source, e.g. join/from/"group by key",
 * {@code nameList} stores {@code 'name', 'manDay'}, and {@code sourceNameList} stores {@code 'p', 't'}
 *
 * @since 4.0.0
 */
@CompileStatic
class NamedRecord<E, T> extends NamedTuple<E> {
    private T sourceRecord
    private List<String> sourceNameList

    NamedRecord(List<E> elementList, List<String> nameList, List<String> sourceNameList = Collections.emptyList()) {
        super(elementList, nameList)
        this.sourceNameList = sourceNameList
    }

    @Override
    def getAt(String name) {
        if (name in nameList) return super.getAt(name)

        if (name !in sourceNameList) {
            throw new IndexOutOfBoundsException("Failed to find: $name")
        }

        return sourceRecord
    }

    T sourceRecord() {
        return sourceRecord
    }

    NamedRecord<E, T> sourceRecord(T sourceRecord) {
        this.sourceRecord = sourceRecord
        return this
    }
}
