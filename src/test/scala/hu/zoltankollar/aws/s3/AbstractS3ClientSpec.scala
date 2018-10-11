package hu.zoltankollar.aws.s3

import java.io.{File, InputStream}

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectResult, S3Object, S3ObjectInputStream}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

import hu.zoltankollar.aws.s3.config.BucketConfig
import hu.zoltankollar.functional.Identity

class AbstractS3ClientSpec extends FlatSpec with Matchers with MockFactory {

  private val config = BucketConfig("test-bucket", "test-prefix/")

  "AbstractS3Client" should "call AmazonS3 getObject on get" in {
    val key = "test-key"
    val amazonS3 = mock[AmazonS3]
    val s3Object = stub[S3Object]
    val s3ObjectContent = stub[S3ObjectInputStream]

    (s3Object.getObjectContent _).when().returns(s3ObjectContent)

    (amazonS3.getObject (_: String, _: String))
      .expects(config.bucketName, config.keyPrefix + key)
      .returning(s3Object)

    val s3 = new AbstractS3Client[Identity](config, amazonS3) {}
    s3.get(key).map(input => input shouldBe s3ObjectContent)
  }

  it should "call AmazonS3 putObject on put String" in {
    val key = "test-key"
    val amazonS3 = mock[AmazonS3]
    val content = ""

    (amazonS3.putObject (_: String, _: String, _: String))
      .expects(config.bucketName, config.keyPrefix + key, content)
      .returning(stub[PutObjectResult])

    val s3 = new AbstractS3Client[Identity](config, amazonS3) {}
    s3.put(key, content).map(_ => succeed)
  }

  it should "call AmazonS3 putObject on put File" in {
    val key = "test-key"
    val amazonS3 = mock[AmazonS3]
    val file = File.createTempFile("AsyncS3Client", ".test")
    file.deleteOnExit()

    (amazonS3.putObject (_: String, _: String, _: File))
      .expects(config.bucketName, config.keyPrefix + key, file)
      .returning(stub[PutObjectResult])

    val s3 = new AbstractS3Client[Identity](config, amazonS3) {}
    s3.put(key, file).map(_ => succeed)
  }

  it should "call AmazonS3 putObject on put InputStream" in {
    val key = "test-key"
    val amazonS3 = mock[AmazonS3]
    val metadata = mock[ObjectMetadata]
    val input = stub[InputStream]

    (amazonS3.putObject (_: String, _: String, _: InputStream, _: ObjectMetadata))
      .expects(config.bucketName, config.keyPrefix + key, input, metadata)
      .returning(stub[PutObjectResult])

    val s3 = new AbstractS3Client[Identity](config, amazonS3) {}
    s3.put(key, input, metadata).map(_ => succeed)
  }

  it should "call AmazonS3 putObject on put Byte Array" in {
    val key = "test-key"
    val amazonS3 = mock[AmazonS3]
    val metadata = mock[ObjectMetadata]
    val input = Array.empty[Byte]

    (amazonS3.putObject (_: String, _: String, _: InputStream, _: ObjectMetadata))
      .expects(config.bucketName, config.keyPrefix + key, *, metadata)
      .returning(stub[PutObjectResult])

    val s3 = new AbstractS3Client[Identity](config, amazonS3) {}
    s3.put(key, input, metadata).map(_ => succeed)
  }

}
