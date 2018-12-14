package gr.di.uoa.m1542m1552.databasesystems.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.di.uoa.m1542m1552.databasesystems.domain.AbandonedVehiclesRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.GarbageCartsRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.GraffitiRemovalRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.PotHolesRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.domain.RodentBaitingRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.SanitationCodeComplaintsRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.TreeDebrisRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.TreeTrimsRequest;
import gr.di.uoa.m1542m1552.databasesystems.domain.User;
import gr.di.uoa.m1542m1552.databasesystems.domain.UserHistory;
import gr.di.uoa.m1542m1552.databasesystems.enumerations.Status;
import gr.di.uoa.m1542m1552.databasesystems.enumerations.TypeOfServiceRequest;
import gr.di.uoa.m1542m1552.databasesystems.service.AbandonedVehiclesRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.GarbageCartsRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.GraffitiRemovalRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.PotHolesRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.RequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.RodentBaitingRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.SanitationCodeComplaintsRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.TreeDebrisRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.TreeTrimsRequestService;
import gr.di.uoa.m1542m1552.databasesystems.service.UserHistoryService;
import gr.di.uoa.m1542m1552.databasesystems.service.UserService;

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
  @Autowired
  UserService userService;
  @Autowired
  UserHistoryService userHistoryService;

  private void setDefaultValues(Request newRequest) {
    newRequest.setServiceRequestNumber(newRequest.getTypeOfServiceRequest() + "-" + new Timestamp(System.currentTimeMillis()));
    newRequest.setLocation("{'latitude':'" + newRequest.getLatitude() + "','longitude':'" + newRequest.getLongitude() + "'}");
    newRequest.setStatus(Status.Open.getText());
  }

  private void recordQuery(String query) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserHistory userHistory = new UserHistory();
    userHistory.setUserId(userService.findByEmail(user.getUsername()));
    userHistory.setTimestamp(new Date());
    userHistory.setQuery(query);
    userHistoryService.createUserHistory(userHistory);
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

  @GetMapping("/search/zipCode={zipCode}&streetAddress={streetAddress}")
  public Page<Request> getRequestsByZipCodeAndStreetAddress(@PageableDefault(value=10, page=0) Pageable pageable,
                                            @PathVariable Integer zipCode,
                                            @PathVariable String streetAddress) throws ServletException {
      recordQuery("searched: {zipCode: " + zipCode + ", address: " + streetAddress + "}");
      Page<Request> page = requestService.getRequestsByZipCodeAndStreetAddress(pageable, zipCode, streetAddress.toUpperCase());
      return page;
  }

  @GetMapping("/search1/fromDate={fromDateStr}&toDate={toDateStr}")
  public Page getRequestsByStoredFunction1(@PageableDefault(value=10, page=0) Pageable pageable,
                                           @PathVariable String fromDateStr,
                                           @PathVariable String toDateStr) throws ServletException  {
    Date fromDate, toDate;
    try {
      fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
    } catch (ParseException e) {
      return null;
	  }  
    try {
      toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr);
    } catch (ParseException e) {
      return null;
    }

    recordQuery("stored function 1: {fromDate: " + fromDate + ", toDate: " + toDate + "}");
    Page page = requestService.getRequestsByStoredFunction1(pageable, fromDate, toDate);
    return page;
  }

  @GetMapping("/search2/fromDate={fromDateStr}&toDate={toDateStr}&type={type}")
  public Page getRequestsByStoredFunction2(@PageableDefault(value=10, page=0) Pageable pageable,
                                           @PathVariable String fromDateStr,
                                           @PathVariable String toDateStr,
                                           @PathVariable TypeOfServiceRequest type) throws ServletException  {
    Date fromDate, toDate;
    try {
      fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
    } catch (ParseException e) {
      return null;
	  }  
    try {
      toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr);
    } catch (ParseException e) {
      return null;
    }

    recordQuery("stored function 2: {fromDate: " + fromDate + ", toDate: " + toDate + ", type: " + type + "}");
    Page page = requestService.getRequestsByStoredFunction2(pageable, fromDate, toDate, type.getText());
    return page;
  }

  @GetMapping("/search3/date={dateStr}")
  public Page getRequestsByStoredFunction2(@PageableDefault(value=10, page=0) Pageable pageable,
                                           @PathVariable String dateStr) throws ServletException  {
    Date date;
    try {
      date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    } catch (ParseException e) {
      return null;
	  }

    recordQuery("stored function 3: {date: " + date + "}");
    Page page = requestService.getRequestsByStoredFunction3(pageable, date);
    return page;
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
