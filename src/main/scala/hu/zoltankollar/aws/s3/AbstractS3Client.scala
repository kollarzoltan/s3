package hu.zoltankollar.aws.s3

import java.io.{ByteArrayInputStream, File, InputStream}

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.apache.logging.log4j.{LogManager, Logger}

import hu.zoltankollar.aws.s3.config.BucketConfig
import hu.zoltankollar.functional.core.Monad
import hu.zoltankollar.functional.syntax._

abstract class AbstractS3Client[M[_] : Monad](
  config: BucketConfig,
  amazonS3: AmazonS3
) extends S3[M] {

  private val log: Logger = LogManager.getLogger(this.getClass)

  override def get(key: String): M[InputStream] = lift {
    log.info(s"Getting object from ${config.bucketName}/${getFullKey(key)} ...")
    amazonS3
      .getObject(config.bucketName, getFullKey(key))
      .getObjectContent
  }

  override def put(key: String, content: String): M[Unit] = lift {
    log.info(s"Putting object to ${config.bucketName}/${getFullKey(key)} ...")
    amazonS3.putObject(config.bucketName, getFullKey(key), content)
  }

  override def put(key: String, file: File): M[Unit] = lift {
    log.info(s"Putting object to ${config.bucketName}/${getFullKey(key)} ...")
    amazonS3.putObject(config.bucketName, getFullKey(key), file)
  }

  override def put(
    key: String,
    input: InputStream,
    metadata: ObjectMetadata
  ): M[Unit] = lift {
    log.info(s"Putting object to ${config.bucketName}/${getFullKey(key)} ...")
    amazonS3.putObject(
      config.bucketName, getFullKey(key), input, metadata
    )
  }

  override def put(
    key: String,
    input: Array[Byte],
    metadata: ObjectMetadata
  ): M[Unit] = lift {
    log.info(s"Putting object to ${config.bucketName}/${getFullKey(key)} ...")
    val stream = new ByteArrayInputStream(input)
    amazonS3.putObject(
      config.bucketName, getFullKey(key), stream, metadata
    )
  }

  private def getFullKey(key: String): String = config.keyPrefix + key

}
