package com.study.admin.ifs;

import com.study.admin.model.network.Header;
import com.study.admin.model.network.request.UserApiRequest;
import com.study.admin.model.network.response.UserApiResponse;

public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);  // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
