/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ymd.RuleChains.controllers

import com.ymd.RuleChains.entities.Chain
import com.ymd.RuleChains.services.ChainService
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.RequestPostProcessor

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.Matchers.*
import static org.junit.Assert.*
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
/**
 *
 * @author jam
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// @ActiveProfiles("test")
class ChainControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private ChainService chainService
  
  @Before
  public void setUp() {
    Chain alex = new Chain("alex");

    when(chainService.getChainByName(alex.getName()))
      .thenReturn(alex);
    when(chainService.listChains())
      .thenReturn([ alex ]);
  }

  @Test
  public void getChain() throws Exception {
    
    mvc.perform(MockMvcRequestBuilders.get("/api/chain/alex")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath('$.name', is("alex")));

  }
  @Test
  public void listChains() throws Exception {
    
    mvc.perform(MockMvcRequestBuilders.get("/api/chain")
      // .with(ssuser())
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("\$", hasSize(1)))
      .andExpect(jsonPath("\$[0].name", is("alex")));

  }
//  public static RequestPostProcessor ssuser() {
//    return user("user").roles("ADMIN");
//  }
}

