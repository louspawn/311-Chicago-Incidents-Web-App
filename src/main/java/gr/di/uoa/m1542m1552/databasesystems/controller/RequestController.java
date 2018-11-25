package gr.di.uoa.m1542m1552.databasesystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.GarbageCartsRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.service.GarbageCartsRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.RequestService;

@RestController
class RequestController {

  @Autowired
  RequestService requestService;
  @Autowired
  GarbageCartsRequestService garbageCartsRequestService;

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

  // GarbageCarts

	@PostMapping("/garbage_carts_requests")
	public GarbageCartsRequest createGarbageCartsRequest(@RequestBody GarbageCartsRequest newRequest) {
		return garbageCartsRequestService.createRequest(newRequest);
	}

  @GetMapping("/requests/{requestId}")
  public GarbageCartsRequest getGarbageCartsRequest(@PathVariable Integer requestId) {
    GarbageCartsRequest request = garbageCartsRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/requests")
  public Iterable<GarbageCartsRequest> getGarbageCartsRequest() {
    Iterable<GarbageCartsRequest> requests = garbageCartsRequestService.getRequests();
    return requests;
  }

}
