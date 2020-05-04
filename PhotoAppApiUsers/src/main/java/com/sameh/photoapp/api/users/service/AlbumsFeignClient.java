package com.sameh.photoapp.api.users.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sameh.photoapp.api.users.exception.AlbumsFallback;
import com.sameh.photoapp.api.users.model.AlbumResponse;

@FeignClient(name="albums-webservice", fallback=AlbumsFallback.class)
public interface AlbumsFeignClient {
	
	@GetMapping(value="/api/users/{userId}/albums")
	public List<AlbumResponse> getAlbums(@PathVariable("userId")String userId);
}
