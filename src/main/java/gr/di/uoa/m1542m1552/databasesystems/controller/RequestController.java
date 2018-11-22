package gr.di.uoa.m1542m1552.databasesystems.controller;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class RequestController {

  @Autowired
  RequestService requestService;

	@PostMapping("/requests")
	public Request createRequest(@RequestBody Request newRequest) {
		return requestService.createRequest(newRequest);
	}

  @GetMapping("/requests/{requestId}")
  public Request getRequest(@PathVariable Integer requestId) {
    Request request = requestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/requests")
  public Iterable<Request> getRequests() {
    Iterable<Request> requests = requestService.getRequests();
    return requests;
  }

}
