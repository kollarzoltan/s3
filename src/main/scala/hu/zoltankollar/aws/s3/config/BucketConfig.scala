package hu.zoltankollar.aws.s3.config

import com.typesafe.config.{Config, ConfigException}

class BucketConfig(
  val bucketName: String,
  val keyPrefix: String
)

object BucketConfig {

  def apply(bucketName: String, keyPrefix: String): BucketConfig =
    new BucketConfig(bucketName, keyPrefix)

  def fromConfig(config: Config): BucketConfig = BucketConfig(
    bucketName = config.getString("name"),
    keyPrefix = getStringOpt(config, "prefix").getOrElse("")
  )

  protected def getStringOpt(
    config: Config,
    key: String
  ): Option[String] = try {
    Option(config.getString(key))
  } catch {
    case _: ConfigException.Missing => None
  }

}
