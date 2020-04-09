package com.shailesh.mvvm_login.viewModel;

import android.view.View;
import com.shailesh.mvvm_login.models.LoginUser;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> EmailAddress = new MutableLiveData<String>();
    public MutableLiveData<String> Password = new MutableLiveData<String>();

    private MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser()
    {
        if(userMutableLiveData == null)
        {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public void onClick(View view)
    {
        LoginUser loginUser = new LoginUser(EmailAddress.getValue(),Password.getValue());

        userMutableLiveData.setValue(loginUser);
    }
}
