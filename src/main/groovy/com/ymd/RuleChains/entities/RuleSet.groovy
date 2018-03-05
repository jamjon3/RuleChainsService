/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ymd.RuleChains.entities

import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.CascadeType
import javax.validation.constraints.Pattern

/**
 *
 * @author James Jones
 */
@Entity
class RuleSet {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id

  @Column(nullable = false, unique=true)
  @Pattern(regexp = "[a-zA-Z0-9]")
  private String name
  @OneToMany(cascade=CascadeType.ALL,mappedBy="ruleSet")
  private Set<Rule> rules
  public long getId() {
    return this.id
  }
  public void setId(long id) {
    this.id = id
  }
  public String getName() {
    return this.name
  }
  public void setName(String name) {
    this.name = name
  }

}
