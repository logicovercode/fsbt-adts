package com.logicovercode.fsbt.commons

sealed trait FSbtModuleIdScope
case object FSbtCompile extends FSbtModuleIdScope
case object FSbtTest extends FSbtModuleIdScope
case object FSbtProvided extends FSbtModuleIdScope

case class Version(version: String)
case class FSbtModuleId(organization : String, artifactId : String, version : Version, crossPath : Boolean, onlyPom : Boolean = false){
  def pomOnly() : FSbtModuleId = {
    FSbtModuleId(organization, artifactId, version, crossPath, true)
  }
}