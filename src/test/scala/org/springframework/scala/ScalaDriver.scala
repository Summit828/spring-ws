package org.springframework.scala

/*
 * Copyright 2011 the original author or authors.
 *
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

import java.net.URI
import jdbc.core.JdbcTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader
import web.client.RestTemplate

object ScalaDriver {

  def jdbcTemplate() {
    val template = new JdbcTemplate();
val result: Seq[String] = template.query("SELECT NAME FROM USERS") {
  (resultSet, i) => {
    resultSet.getNString("NAME")
  }
}
    println(result)
  }

  def main(args: Array[String]) {
    val template = new RestTemplate()
    //    template.delete("http://localhost")
    val result = template.execute(new URI("http://localhost"), HttpMethod.GET, null) {
      response: ClientHttpResponse => FileCopyUtils.copyToString(new InputStreamReader(response.getBody))
    }
    println(result.getClass)


  }
}