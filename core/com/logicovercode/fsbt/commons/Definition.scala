package com.logicovercode.fsbt.commons

import com.logicovercode.wdocker.{ContainerDefinition, DockerNetwork}

import scala.concurrent.duration.FiniteDuration

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

case class SbtServiceDescription(container: ContainerDefinition,
                                 imagePullTimeout : FiniteDuration,
                                 containerStartTimeout : FiniteDuration)

trait SbtMicroservice{
  def sbtServiceDescriptions : Seq[SbtServiceDescription]
  def networks() : Seq[DockerNetwork] = {
    (for{
      serviceDescription <- sbtServiceDescriptions
      network = serviceDescription.container.networkMode
    } yield network).flatten
  }
}

case class ClusterService(override val sbtServiceDescriptions : Seq[SbtServiceDescription]) extends SbtMicroservice

case class SbtFlywayConfig(url : String,
                            userName : String, password : String,
                            locations : Seq[String]){
  val baseLineOnMigrate : Boolean = true
  val baseLineVersion = "0"
}
case class DbService(private val sbtServiceDescription : SbtServiceDescription, sbtFlywayConfig: SbtFlywayConfig) extends SbtMicroservice {
  override def sbtServiceDescriptions: Seq[SbtServiceDescription] = Seq(sbtServiceDescription)
}

case class FSbtResolver(name : String, url : String)
case class FSbtLicense(name : String, url : String)

