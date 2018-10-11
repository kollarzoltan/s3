package hu.zoltankollar.aws.s3

import javax.inject.Inject

import scala.util.Try

import com.amazonaws.services.s3.AmazonS3

import hu.zoltankollar.aws.s3.config.BucketConfig
import hu.zoltankollar.functional.TryMonad.tryMonad

class SyncS3Client @Inject()(
  config: BucketConfig,
  amazonS3: AmazonS3
) extends AbstractS3Client[Try](config, amazonS3) with SyncS3
