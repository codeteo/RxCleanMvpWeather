package cs.rxclean.rxcleanmvpweatherapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cs.rxclean.rxcleanmvpweatherapp.R;
import cs.rxclean.rxcleanmvpweatherapp.main.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_main_container) FrameLayout flMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getFragmentManager().beginTransaction()
                .add(R.id.fl_main_container, MainFragment.newInstance())
                .commit();

    }
}
