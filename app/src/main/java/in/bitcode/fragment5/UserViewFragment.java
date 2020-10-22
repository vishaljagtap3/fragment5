package in.bitcode.fragment5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserViewFragment extends Fragment {

    private TextView mTxtUserName, mTxtUserPhone;
    private Button mBtnUpdateInfo;

    public interface OnDataUpdateListener {
        public void onDataUpdate(String userName, String phone);
    }

    private OnDataUpdateListener mOnDataUpdateListener;

    public void setOnDataUpdateListener(OnDataUpdateListener onDataUpdateListener) {
        this.mOnDataUpdateListener = onDataUpdateListener;
    }


    //way 2
    //private String mUserName, mUserPhone;

    //way 2
    /*
    public UserViewFragment(String userName, String userPhone) {
        mUserName = userName;
        mUserPhone = userPhone;
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_view, null);

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mTxtUserName = view.findViewById(R.id.txtUserName);
        mTxtUserPhone = view.findViewById(R.id.txtUserPhone);
        mBtnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        mBtnUpdateInfo.setOnClickListener(new OnBtnUpdateInfoClickListener());

        //way 2
        /*mTxtUserName.setText(mUserName);
        mTxtUserPhone.setText(mUserPhone);*/

        //way 1

        Bundle input = getArguments();
        if (input != null) {
            mTxtUserName.setText(input.getString("name"));
            mTxtUserPhone.setText(input.getString("phone"));
        }

        return view;
    }

    private class OnBtnUpdateInfoClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (mOnDataUpdateListener != null) {
                mOnDataUpdateListener.onDataUpdate(
                        mTxtUserName.getText().toString().toUpperCase(),
                        mTxtUserPhone.getText().toString() + " India"
                );
            }

            getFragmentManager().beginTransaction()
                    .remove(UserViewFragment.this)
                    .commit();

        }
    }
}
