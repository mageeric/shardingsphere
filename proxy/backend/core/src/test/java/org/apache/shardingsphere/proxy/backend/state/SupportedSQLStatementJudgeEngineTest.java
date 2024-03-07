/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.state;

import org.apache.shardingsphere.sql.parser.sql.common.statement.SQLStatement;
import org.apache.shardingsphere.sql.parser.sql.common.statement.dml.DeleteStatement;
import org.apache.shardingsphere.sql.parser.sql.common.statement.dml.GenericSelectStatement;
import org.apache.shardingsphere.sql.parser.sql.common.statement.dml.UpdateStatement;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class SupportedSQLStatementJudgeEngineTest {
    
    @Test
    void assertIsSupportedWithInSupportedList() {
        assertTrue(new SupportedSQLStatementJudgeEngine(Collections.singleton(GenericSelectStatement.class), Collections.emptyList()).isSupported(mock(GenericSelectStatement.class)));
    }
    
    @Test
    void assertIsNotSupportedWithInUnsupportedList() {
        assertFalse(new SupportedSQLStatementJudgeEngine(Collections.emptyList(), Collections.singleton(GenericSelectStatement.class)).isSupported(mock(GenericSelectStatement.class)));
    }
    
    @Test
    void assertIsSupportedWithOverlappedList() {
        assertTrue(
                new SupportedSQLStatementJudgeEngine(Collections.singleton(GenericSelectStatement.class), Collections.singleton(SQLStatement.class)).isSupported(mock(GenericSelectStatement.class)));
    }
    
    @Test
    void assertIsSupportedWithoutList() {
        assertTrue(new SupportedSQLStatementJudgeEngine(Collections.singleton(GenericSelectStatement.class), Collections.singleton(UpdateStatement.class)).isSupported(mock(DeleteStatement.class)));
    }
}
