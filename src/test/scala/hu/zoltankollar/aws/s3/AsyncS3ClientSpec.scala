package hu.zoltankollar.aws.s3

import scala.concurrent.{ExecutionContext, Future}

import com.amazonaws.services.s3.AmazonS3
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

import hu.zoltankollar.aws.s3.config.BucketConfig

class AsyncS3ClientSpec extends FlatSpec with Matchers with MockFactory {


  "AsyncS3Client" should "be an AbstractS3Client[Future]" in {
    val config = BucketConfig("test-bucket", "test-prefix/")
    val amazonS3 = mock[AmazonS3]
    implicit val ec: ExecutionContext = mock[ExecutionContext]

    new AsyncS3Client(config, amazonS3) match {
      case _: AbstractS3Client[Future] => succeed
      case _ => fail()
    }
  }

}
