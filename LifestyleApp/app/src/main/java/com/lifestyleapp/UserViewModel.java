package com.lifestyleapp;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private LiveData<User> userLiveData;
    private UserRepository profilePageRepository;

    public UserViewModel(Application application) {

        super(application);

        profilePageRepository = UserRepository.getInstance();

        userLiveData = profilePageRepository.getUserData();

    }

    // FORWARD ALL OF THE DATA TO THE REPOSITORY
    public void setProfileViewModelData(String fullName, int age, String city, String country, double height, double weight, int gender, @Nullable String profilePhotoFileName, @Nullable int profilePhotoSize, @Nullable Integer steps, double bmi, double bmr, boolean sedentary, double pounds){
        profilePageRepository.setUserData(fullName, age, city, country, height, weight, gender, profilePhotoFileName, profilePhotoSize, steps, sedentary,pounds);
    }

    // RETRIEVE DATA FROM THE REPOSITORY
    public LiveData<User> getProfileViewModelData() {
        return UserRepository.getInstance().getUserData();
    }

    public void setSedentary(Boolean sedentary){
        User user=userLiveData.getValue();
        profilePageRepository.setUserData(user.getFullName(), user.getAge(), user.getCity(), user.getCountry(), user.getHeight(), user.getWeight(), user.getGender(), user.getProfilePhotoPath(), user.getProfilePhotoSize(), user.getSteps(), sedentary,user.getPounds());


    }

    public void setPounds(double pound){
        User user=userLiveData.getValue();
        profilePageRepository.setUserData(user.getFullName(), user.getAge(), user.getCity(), user.getCountry(), user.getHeight(), user.getWeight(), user.getGender(), user.getProfilePhotoPath(), user.getProfilePhotoSize(), user.getSteps(), user.getSedentary(),pound);


    }


    public String getCity() {

        String city = "Salt Lake City";  // default if there is no user

        if (getProfileViewModelData().getValue() != null) {

            return getProfileViewModelData().getValue().getCity();

        }

        return city;
    }




}
