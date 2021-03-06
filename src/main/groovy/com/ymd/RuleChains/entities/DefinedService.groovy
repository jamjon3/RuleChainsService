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
import javax.persistence.FetchType
import javax.persistence.CascadeType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import org.springframework.web.bind.annotation.RequestMethod

import com.ymd.RuleChains.enums.ParseEnum
/**
 *
 * @author James Jones
 */
@Entity
class DefinedService extends Rule {
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
	private RequestMethod requestMethod
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ParseEnum parseEnum
  private String url = ""
  @OneToMany(
    // mappedBy="definedService",
    cascade=CascadeType.ALL
  )
  private Set<Link> links
  public RequestMethod getRequestMethod() {
    return this.requestMethod
  }
  public void setRequestMethod(RequestMethod requestMethod) {
    this.requestMethod = requestMethod
  }
  public ParseEnum getParseEnum() {
    return this.parseEnum
  }
  public void setParseEnum(ParseEnum parseEnum) {
    this.parseEnum = parseEnum
  }
  public String getUrl() {
    return this.url
  }
  public void setUrl(String url) {
    this.url = url
  }
  public Set<Link> getLinks() {
    return this.links
  }
  public void setLinks(Set<Link> links) {
    this.links = links
  }
}

