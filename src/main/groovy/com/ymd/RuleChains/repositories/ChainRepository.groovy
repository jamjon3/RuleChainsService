/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ymd.RuleChains.repositories

import com.ymd.RuleChains.entities.Chain
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 *
 * @author jam
 */
@Repository
@Transactional
interface ChainRepository extends JpaRepository<Chain, Long> {
  Chain findByName(String name)
  List<Chain> findAll()
  @Modifying
  @Query("update Chain c set c.name = :newname where c.name = :oldname")
  void updateName(@Param("oldname") String oldname, @Param("newname") String newname)
}

