package hu.zoltankollar.aws.s3

import java.io.{File, InputStream}

import com.amazonaws.{AmazonServiceException, SdkClientException}
import com.amazonaws.services.s3.model.ObjectMetadata

trait S3[M[_]] {

  /**
    * Gets the object stored under key.
    *
    * @throws SdkClientException
    *   If any errors are encountered in the client while making the
    *   request or handling the response.
    *
    * @throws AmazonServiceException
    *  If any errors occurred in Amazon S3 while processing the
    *  request.
    */
  def get(key: String): M[InputStream]

  /**
    * Uploads the specified input string to Amazon S3 under
    * the specified key.
    *
    * @throws SdkClientException
    *   If any errors are encountered in the client while making the
    *   request or handling the response.
    *
    * @throws AmazonServiceException
    *  If any errors occurred in Amazon S3 while processing the
    *  request.
    */
  def put(key: String, content: String): M[Unit]

  /**
    * Uploads the specified input file to Amazon S3 under
    * the specified key.
    *
    * @throws SdkClientException
    *   If any errors are encountered in the client while making the
    *   request or handling the response.
    *
    * @throws AmazonServiceException
    *  If any errors occurred in Amazon S3 while processing the
    *  request.
    */
  def put(key: String, file: File): M[Unit]

  /**
    * Uploads the specified input stream and metadata to Amazon S3 under
    * the specified key.
    *
    * @throws SdkClientException
    *   If any errors are encountered in the client while making the
    *   request or handling the response.
    *
    * @throws AmazonServiceException
    *  If any errors occurred in Amazon S3 while processing the
    *  request.
    */
  def put(key: String, input: InputStream, metadata: ObjectMetadata): M[Unit]

  /**
    * Uploads the specified bytes with metadata to Amazon S3 under
    * the specified key.
    *
    * @throws SdkClientException
    *   If any errors are encountered in the client while making the
    *   request or handling the response.
    *
    * @throws AmazonServiceException
    *  If any errors occurred in Amazon S3 while processing the
    *  request.
    */
  def put(key: String, input: Array[Byte], metadata: ObjectMetadata): M[Unit]

}
