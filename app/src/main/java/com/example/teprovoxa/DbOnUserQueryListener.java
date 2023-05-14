package com.example.teprovoxa;

import java.util.List;

public interface DbOnUserQueryListener {
    void onSuccess(List<UserEntity> items);
}
