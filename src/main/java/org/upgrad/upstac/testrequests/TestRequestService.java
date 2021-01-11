package org.upgrad.upstac.testrequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestRequestService {
    @Autowired
    TestRequestRepository testRequestRepository;

    public TestRequest saveTestRequest(User loggedInUser, TestRequestInput testRequestInput) {
        if (!validateTestRequestCanBeSaved(testRequestInput))
            throw new AppException("A Request with same Email or Phone Number already exists.");

        TestRequest testRequest = new TestRequest();

        testRequest.setName(testRequestInput.getName());
        testRequest.setGender(testRequestInput.getGender());
        testRequest.setAddress(testRequestInput.getAddress());
        testRequest.setAge(testRequestInput.getAge());
        testRequest.setEmail(testRequestInput.getEmail());
        testRequest.setPhoneNumber(testRequestInput.getPhoneNumber());
        testRequest.setPinCode(testRequestInput.getPinCode());
        testRequest.setCreatedBy(loggedInUser);
        testRequest.setCreated(LocalDate.now());
        testRequest.setStatus(RequestStatus.INITIATED);

        return testRequestRepository.save(testRequest);
    }

    public boolean validateTestRequestCanBeSaved(TestRequestInput testRequestInput) {
        List<TestRequest> testRequests = testRequestRepository.findByEmailOrPhoneNumber(testRequestInput.getEmail(), testRequestInput.getPhoneNumber());

        for (TestRequest testRequest : testRequests) {
            if (!testRequest.getStatus().equals(RequestStatus.COMPLETED)) {
                return false;
            }
        }

        return true;
    }
}
