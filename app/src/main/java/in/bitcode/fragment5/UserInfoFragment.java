package in.bitcode.fragment5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserInfoFragment extends Fragment {

    private EditText mEdtUserName, mEdtUserPhone;
    private Button mBtnSendData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, null);

        mEdtUserName = view.findViewById(R.id.edtUserName);
        mEdtUserPhone = view.findViewById(R.id.edtUserPhone);
        mBtnSendData = view.findViewById(R.id.btnSendData);

        mBtnSendData.setOnClickListener(new OnBtnSendDataClickListener());

        return view;
    }

    private class OnBtnSendDataClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //way 1
            UserViewFragment userViewFragment = new UserViewFragment();

            Bundle input = new Bundle();
            input.putString("name", mEdtUserName.getText().toString());
            input.putString("phone", mEdtUserPhone.getText().toString());
            userViewFragment.setArguments(input);

            userViewFragment.setOnDataUpdateListener(new MyDataUpdateListener());

            //way 2
            /*UserViewFragment userViewFragment =
                    new UserViewFragment(
                            mEdtUserName.getText().toString(),
                            mEdtUserPhone.getText().toString()
                    );*/

            getFragmentManager().beginTransaction()
                    .add(R.id.mainContainer, userViewFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }

    private class MyDataUpdateListener implements UserViewFragment.OnDataUpdateListener {
        @Override
        public void onDataUpdate(String userName, String phone) {
            mEdtUserName.setText(userName);
            mEdtUserPhone.setText(phone);
        }
    }
}
