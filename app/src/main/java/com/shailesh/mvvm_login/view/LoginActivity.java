package com.shailesh.mvvm_login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.shailesh.mvvm_login.R;
import com.shailesh.mvvm_login.models.LoginUser;
import com.shailesh.mvvm_login.viewModel.LoginViewModel;
import com.shailesh.mvvm_login.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity
{
    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(LoginActivity.this,R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>()
        {
            @Override
            public void onChanged(LoginUser loginUser)
            {
                if(TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress()))
                {
                    binding.txtEmailAddress.setError("Enter an E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if(!loginUser.isEmailValid())
                {
                    binding.txtEmailAddress.setError("Enter a Valid E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if(TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword()))
                {
                    binding.txtPassword.setError("Enter as Password");
                    binding.txtPassword.requestFocus();
                }
                else if(!loginUser.isPasswordLengthGreaterThan5())
                {
                    binding.txtPassword.setError("Enter Valid Password");
                    binding.txtPassword.requestFocus();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, loginUser.getStrEmailAddress()+" "+loginUser.getStrPassword(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
