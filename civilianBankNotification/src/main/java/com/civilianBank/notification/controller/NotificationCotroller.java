package com.civilianBank.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civilianBank.notification.service.NotificationService;

@RestController
public class NotificationCotroller {
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/conversation/create")
	public void createNotification() {

	}


	@RequestMapping(value = "/notification/create")
	public void getUserNotification() {

	}

}
