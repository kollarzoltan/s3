package hu.zoltankollar.aws.s3

import scala.concurrent.ExecutionContext
import scala.util.Try

import com.amazonaws.services.s3.AmazonS3
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

import hu.zoltankollar.aws.s3.config.BucketConfig

class SyncS3ClientSpec extends FlatSpec with Matchers with MockFactory {


  "SyncS3Client" should "be an AbstractS3Client[Try]" in {
    val config = BucketConfig("test-bucket", "test-prefix/")
    val amazonS3 = mock[AmazonS3]
    implicit val ec: ExecutionContext = mock[ExecutionContext]

    new SyncS3Client(config, amazonS3) match {
      case _: AbstractS3Client[Try] => succeed
      case _ => fail()
    }
  }

}
