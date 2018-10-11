package hu.zoltankollar.aws.s3

import scala.concurrent.Future

trait AsyncS3 extends S3[Future]
