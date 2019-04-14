/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.catalyst.plans.logical
// scalastyle:off

import org.apache.spark.sql.catalyst.expressions.Attribute

sealed trait EmmType
case object EmmTSelect extends EmmType

case class CashJoin(input: LogicalPlan, emm: LogicalPlan, emmType: EmmType, inputKey: Attribute, emmKey: Attribute) extends BinaryNode {

  // todo: for t_m of joinning a new table, need to add new rid to output
  override def output: Seq[Attribute] = emmType match {
    case EmmTSelect => emm.output
  }

  override def left: LogicalPlan = input

  override def right: LogicalPlan = emm
}
