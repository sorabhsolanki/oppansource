package com.oppan.message.publisher;

import com.oppan.exception.UploadRequestException;

public interface IPublisher<T> {

  void publishMessage(T t, String topic) throws UploadRequestException;

}
