package org.upgrad.upstac.testrequests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestRequestServiceTest {
    @InjectMocks
    TestRequestService testRequestService;

    @Mock
    TestRequestRepository testRequestRepository;

    @Test
    public void when_saveTestRequest_called_with_valid_parameters_expect_save_method_of_testRequestRepository_is_called() {
        //Arrange
        User loggedInUser = createUser();

        TestRequestInput testRequestInput = createMockedTestRequestInput();

        when(testRequestRepository.findByEmailOrPhoneNumber(testRequestInput.getEmail(), testRequestInput.getPhoneNumber())).thenReturn(new ArrayList<>());

        //Act
        testRequestService.saveTestRequest(loggedInUser, testRequestInput);

        //Assert
        verify(testRequestRepository,times(1)).save(any());
    }

    @Test
    public void when_saveTestRequest_called_with_same_values_in_database_expect_AppException_is_thrown() {
        //Arrange
        User loggedInUser = createUser();

        TestRequestInput testRequestInput = createMockedTestRequestInput();

        TestRequest testRequest = new TestRequest();
        testRequest.setStatus(RequestStatus.INITIATED);
        testRequest.setEmail("someone@somemail.com");
        testRequest.setPhoneNumber("8433589652");

        List<TestRequest> mockedTestRequestsForFinByEmailOrPhoneNumber = new ArrayList<>();
        mockedTestRequestsForFinByEmailOrPhoneNumber.add(testRequest);

        when(testRequestRepository.findByEmailOrPhoneNumber(testRequestInput.getEmail(), testRequestInput.getPhoneNumber())).thenReturn(mockedTestRequestsForFinByEmailOrPhoneNumber);

        //Act
        AppException appException = assertThrows(AppException.class, ()-> {
            testRequestService.saveTestRequest(loggedInUser, testRequestInput);
        });

        //Assert
        assertThat(appException.getMessage(), containsString("A request with same email or phone number already exists."));
    }

    private TestRequestInput createMockedTestRequestInput() {
        TestRequestInput testRequestInput = new TestRequestInput();
        testRequestInput.setAddress("some address");
        testRequestInput.setAge(87);
        testRequestInput.setEmail("someone@somemail.com");
        testRequestInput.setPhoneNumber("8433589652");
        return testRequestInput;
    }

    private User createUser() {
        User loggedInUser = new User();
        loggedInUser.setUserName("someone");
        return loggedInUser;
    }
}