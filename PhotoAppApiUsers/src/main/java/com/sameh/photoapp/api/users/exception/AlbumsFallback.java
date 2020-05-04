package com.sameh.photoapp.api.users.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sameh.photoapp.api.users.model.AlbumResponse;
import com.sameh.photoapp.api.users.service.AlbumsFeignClient;

@Component
public class AlbumsFallback implements AlbumsFeignClient {
	//if Albums microservice is down, AlbumsFeignCLient falls back to this implementation
	@Override
	public List<AlbumResponse> getAlbums(String userId) {
		//Return empty list
		return new ArrayList<>();
	}

}
