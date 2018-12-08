package gr.di.uoa.m1542m1552.databasesystems.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.*;
import gr.di.uoa.m1542m1552.databasesystems.service.*;

@RestController
class RequestController {

  @Autowired
  RequestService requestService;
  @Autowired
  GarbageCartsRequestService garbageCartsRequestService;
  @Autowired
  AbandonedVehiclesRequestService abandonedVehiclesRequestService;
  @Autowired
  GraffitiRemovalRequestService graffitiRemovalRequestService;
  @Autowired
  PotHolesRequestService potHolesRequestService;
  @Autowired
  RodentBaitingRequestService rodentBaitingRequestService;
  @Autowired
  SanitationCodeComplaintsRequestService sanitationCodeComplaintsRequestService;
  @Autowired
  TreeDebrisRequestService treeDebrisRequestService;
  @Autowired
  TreeTrimsRequestService treeTrimsRequestService;

  private void setDefaultValues(Request newRequest) {
    newRequest.setServiceRequestNumber(newRequest.getTypeOfServiceRequest() + "-" + new Timestamp(System.currentTimeMillis()));
    newRequest.setLocation("{'latitude':'" + newRequest.getLatitude() + "','longitude':'" + newRequest.getLongitude() + "'}");
    newRequest.setStatus(Status.Open.getText());
  }

  //Requests
	@PostMapping("/requests")
	public Request createRequest(@RequestBody Request newRequest) {
    setDefaultValues(newRequest);
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

  // Abandoned Vehicles 
	@PostMapping("/abandoned_vehicles_requests")
	public AbandonedVehiclesRequest createAbandonedVehiclesRequest(@RequestBody AbandonedVehiclesRequest newRequest) {
    setDefaultValues(newRequest);
		return abandonedVehiclesRequestService.createRequest(newRequest);
	}

  @GetMapping("/abandoned_vehicles_requests/{requestId}")
  public AbandonedVehiclesRequest getAbandonedVehiclesRequest(@PathVariable Integer requestId) {
    AbandonedVehiclesRequest request = abandonedVehiclesRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/abandoned_vehicles_requests")
  public Iterable<AbandonedVehiclesRequest> getAbandonedVehiclesRequest() {
    Iterable<AbandonedVehiclesRequest> requests = abandonedVehiclesRequestService.getRequests();
    return requests;
  }

  // Garbage Carts
	@PostMapping("/garbage_carts_requests")
	public GarbageCartsRequest createGarbageCartsRequest(@RequestBody GarbageCartsRequest newRequest) {
    setDefaultValues(newRequest);
		return garbageCartsRequestService.createRequest(newRequest);
	}

  @GetMapping("/garbage_carts_requests/{requestId}")
  public GarbageCartsRequest getGarbageCartsRequest(@PathVariable Integer requestId) {
    GarbageCartsRequest request = garbageCartsRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/garbage_carts_requests")
  public Iterable<GarbageCartsRequest> getGarbageCartsRequest() {
    Iterable<GarbageCartsRequest> requests = garbageCartsRequestService.getRequests();
    return requests;
  }

  // Graffiti Removal
	@PostMapping("/graffiti_removal_requests")
	public GraffitiRemovalRequest createGraffitiRemovalRequest(@RequestBody GraffitiRemovalRequest newRequest) {
    setDefaultValues(newRequest);
		return graffitiRemovalRequestService.createRequest(newRequest);
	}

  @GetMapping("/graffiti_removal_requests/{requestId}")
  public GraffitiRemovalRequest getGraffitiRemovalRequest(@PathVariable Integer requestId) {
    GraffitiRemovalRequest request = graffitiRemovalRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/graffiti_removal_requests")
  public Iterable<GraffitiRemovalRequest> getGraffitiRemovalRequest() {
    Iterable<GraffitiRemovalRequest> requests = graffitiRemovalRequestService.getRequests();
    return requests;
  }

  // Pot Holes
	@PostMapping("/pot_holes_requests")
	public PotHolesRequest createPotHolesRequest(@RequestBody PotHolesRequest newRequest) {
    setDefaultValues(newRequest);
		return potHolesRequestService.createRequest(newRequest);
	}

  @GetMapping("/pot_holes_requests/{requestId}")
  public PotHolesRequest getPotHolesRequest(@PathVariable Integer requestId) {
    PotHolesRequest request = potHolesRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/pot_holes_requests")
  public Iterable<PotHolesRequest> getPotHolesRequest() {
    Iterable<PotHolesRequest> requests = potHolesRequestService.getRequests();
    return requests;
  }

  // Rodent Baiting
	@PostMapping("/rodent_baiting_requests")
	public RodentBaitingRequest createRodentBaitingRequest(@RequestBody RodentBaitingRequest newRequest) {
    setDefaultValues(newRequest);
		return rodentBaitingRequestService.createRequest(newRequest);
	}

  @GetMapping("/rodent_baiting_requests/{requestId}")
  public RodentBaitingRequest getRodentBaitingRequest(@PathVariable Integer requestId) {
    RodentBaitingRequest request = rodentBaitingRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/rodent_baiting_requests")
  public Iterable<RodentBaitingRequest> getRodentBaitingRequest() {
    Iterable<RodentBaitingRequest> requests = rodentBaitingRequestService.getRequests();
    return requests;
  }

  // Sanitation Code Complaints
	@PostMapping("/sanitation_code_complaints_requests")
	public SanitationCodeComplaintsRequest createSanitationCodeComplaintsRequest(@RequestBody SanitationCodeComplaintsRequest newRequest) {
    setDefaultValues(newRequest);
		return sanitationCodeComplaintsRequestService.createRequest(newRequest);
	}

  @GetMapping("/sanitation_code_complaints_requests/{requestId}")
  public SanitationCodeComplaintsRequest getSanitationCodeComplaintsRequest(@PathVariable Integer requestId) {
    SanitationCodeComplaintsRequest request = sanitationCodeComplaintsRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/sanitation_code_complaints_requests")
  public Iterable<SanitationCodeComplaintsRequest> getSanitationCodeComplaintsRequest() {
    Iterable<SanitationCodeComplaintsRequest> requests = sanitationCodeComplaintsRequestService.getRequests();
    return requests;
  }

  // Tree Debris
	@PostMapping("/tree_debris_requests")
	public TreeDebrisRequest createTreeDebrisRequest(@RequestBody TreeDebrisRequest newRequest) {
    setDefaultValues(newRequest);
		return treeDebrisRequestService.createRequest(newRequest);
	}

  @GetMapping("/tree_debris_requests/{requestId}")
  public TreeDebrisRequest getTreeDebrisRequest(@PathVariable Integer requestId) {
    TreeDebrisRequest request = treeDebrisRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/tree_debris_requests")
  public Iterable<TreeDebrisRequest> getTreeDebrisRequest() {
    Iterable<TreeDebrisRequest> requests = treeDebrisRequestService.getRequests();
    return requests;
  }

  // Tree Trims
	@PostMapping("/tree_trims_requests")
	public TreeTrimsRequest createTreeTrimsRequest(@RequestBody TreeTrimsRequest newRequest) {
    setDefaultValues(newRequest);
		return treeTrimsRequestService.createRequest(newRequest);
	}

  @GetMapping("/tree_trims_requests/{requestId}")
  public TreeTrimsRequest getTreeTrimsRequest(@PathVariable Integer requestId) {
    TreeTrimsRequest request = treeTrimsRequestService.getRequest(requestId);
    return request;
  }

  @GetMapping("/tree_trims_requests")
  public Iterable<TreeTrimsRequest> getTreeTrimsRequest() {
    Iterable<TreeTrimsRequest> requests = treeTrimsRequestService.getRequests();
    return requests;
  }

}
