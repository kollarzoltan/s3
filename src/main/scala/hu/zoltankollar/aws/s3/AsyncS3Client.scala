package hu.zoltankollar.aws.s3

import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

import com.amazonaws.services.s3.AmazonS3

import hu.zoltankollar.aws.s3.config.BucketConfig
import hu.zoltankollar.functional.FutureMonad.futureMonad

class AsyncS3Client @Inject()(
  config: BucketConfig,
  amazonS3: AmazonS3
)(implicit ec: ExecutionContext)
  extends AbstractS3Client[Future](config, amazonS3)
    with AsyncS3
