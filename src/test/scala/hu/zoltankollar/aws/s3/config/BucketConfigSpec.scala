package hu.zoltankollar.aws.s3.config

import scala.collection.JavaConverters.mapAsJavaMap

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

class BucketConfigSpec extends FlatSpec with Matchers {

  private val name: String = "test-bucket-name"
  private val prefix: String = "test-key-prefix"

  "BucketConfig" should "be created from Config" in {
    val values = mapAsJavaMap(
      Map(
        "name" -> name,
        "prefix" -> prefix
      )
    )

    val config = ConfigFactory.parseMap(values)
    val bucketConfig = BucketConfig.fromConfig(config)
    bucketConfig.bucketName shouldBe name
    bucketConfig.keyPrefix shouldBe prefix
  }

  it should "be created by Config with empty prefix if it is not present" in {
    val values = mapAsJavaMap(
      Map(
        "name" -> name
      )
    )

    val config = ConfigFactory.parseMap(values)
    val bucketConfig = BucketConfig.fromConfig(config)
    bucketConfig.bucketName shouldBe name
    bucketConfig.keyPrefix shouldBe ""
  }

}
