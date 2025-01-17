/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.streams.kstream.internals.graph;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.Record;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableProcessorNodeTest {
    private static class TestProcessor implements Processor<String, String, String, String> {

        @Override
        public void process(final Record<String, String> record) {
        }

    }

    @Test
    public void shouldConvertToStringWithNullStoreBuilder() {
        final TableProcessorNode<String, String> node = new TableProcessorNode<>(
            "name",
            new ProcessorParameters<>(TestProcessor::new, "processor"),
            null,
            new String[]{"store1", "store2"}
        );

        final String asString = node.toString();
        final String expected = "storeFactory=null";
        assertTrue(
                asString.contains(expected),
                String.format(
                        "Expected toString to return string with \"%s\", received: %s",
                        expected,
                        asString)
        );
    }
}