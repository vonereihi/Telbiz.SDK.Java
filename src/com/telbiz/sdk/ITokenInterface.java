package com.telbiz.sdk;

import com.telbiz.model.RefreshTokenEndPointRequest;
import com.telbiz.model.TokenEndPointRequest;
import com.telbiz.model.TokenResponse;

public interface ITokenInterface {
	TokenResponse GetAccessToken(TokenEndPointRequest request);
	TokenResponse GetRefreshToken(RefreshTokenEndPointRequest request);
}
