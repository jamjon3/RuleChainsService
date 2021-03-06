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

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.CascadeType
import javax.validation.constraints.Pattern

/**
 *
 * @author James Jones
 */
@Entity
@Inheritance
abstract class Rule {
	@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id

  @Column(nullable = false, unique=true)
  @Pattern(regexp = "[a-zA-Z0-9]")
  private String name
  
  @OneToOne(optional=false)
  private RuleSet ruleSet
  
  // @OneToMany(cascade=CascadeType.ALL,mappedBy="rule")
  // private Set<Link> links
  
  public Long getId() {
    return this.id
  }
  public void setId(Long id) {
    this.id = id
  }
  public String getName() {
    return this.id
  }
  public void setName(String name) {
    this.name = name
  }
  public RuleSet getRuleSet() {
    return this.ruleSet
  }
  public void setRuleSet(RuleSet ruleSet) {
    this.ruleSet = ruleSet
  }
//  public Set<Link> getLinks() {
//    return this.links
//  }
//  public void setLinks(Set<Link> links) {
//    this.links = links
//  }
}

