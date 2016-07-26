package com.example.sg280.fotile.presents;

import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.source.RegisterSouce;
import com.example.sg280.fotile.model.source.RegisterSourceImp;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterPresent  implements RegisterContracts.Present{


    private  RegisterSourceImp source;
    private RegisterContracts.View mview;

    public RegisterPresent(RegisterContracts.View view){
     this.mview=view;
        source=new RegisterSourceImp();
    }

    @Override
    public void start() {

    }

    @Override
    public void ondestory() {

    }

    @Override
    public void getDycode(String tel) {
          source.getDycode(tel, new RegisterSouce.DataListener() {
              @Override
              public void success() {


              }

              @Override
              public void failed(String msg) {
                        mview.failed(msg);
              }

              @Override
              public void noResponse() {
                       mview.failed(Constants.NET_ERROR);
              }
          });
    }

    @Override
    public void userValid(final String tel, final String dycode,final String pwd) {
                source.userValid(tel, dycode, new RegisterSouce.DataListener() {
                    @Override
                    public void success() {
                        //验证成功后注册
                        register(tel,dycode,pwd);
                    }

                    @Override
                    public void failed(String msg) {
                             mview.failed(msg);
                    }

                    @Override
                    public void noResponse() {
                          mview.failed(Constants.NET_ERROR);
                    }
                });
    }
    @Override
    public void register(String tel, String code, String pwd) {

    }
}
