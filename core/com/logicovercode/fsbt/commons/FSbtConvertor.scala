package com.logicovercode.fsbt.commons

trait FSbtConvertor {

  case class FSbtModuleIdMeta(organization : String, artifactId : String, crossPaths : Boolean)
  case class FSbtModuleIdWithoutScope(organization : String, artifactId : String, version : Version, crossPaths : Boolean)

  implicit class StringFSbtExtension(str : String){

    def %%(artifactId : String) : FSbtModuleIdMeta = {
      FSbtModuleIdMeta(str, artifactId, true)
    }

    def %(artifactId : String) : FSbtModuleIdMeta = {
      FSbtModuleIdMeta(str, artifactId, false)
    }
  }

  implicit class FSbtModuleIdMetaExtension(fSbtModuleIdMeta: FSbtModuleIdMeta){

    def %(version : String) : FSbtModuleId = {
      FSbtModuleId(fSbtModuleIdMeta.organization, fSbtModuleIdMeta.artifactId, Version(version), fSbtModuleIdMeta.crossPaths)
    }
  }
}

object FSbtConvertor extends FSbtConvertor
