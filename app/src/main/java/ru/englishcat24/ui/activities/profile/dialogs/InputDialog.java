package ru.englishcat24.ui.activities.profile.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.englishcat24.R;

/**
 * Created by crish on 1/23/18.
 */

public class InputDialog extends DialogFragment {
    public static final String INPUT_TYPE = "INPUT_TYPE";
    public static final String HINT = "HINT";

    public static final InputDialog newInstance(int hintResId, int inputType) {
        Bundle bundle = new Bundle();
        bundle.putInt(HINT, hintResId);
        bundle.putInt(INPUT_TYPE, inputType);

        InputDialog inputDialog = new InputDialog();
        inputDialog.setArguments(bundle);

        return inputDialog;
    }

    @BindView(R.id.tvNegativeButton)
    TextView tvNegativeButton;
    @BindView(R.id.tvPositionButton)
    TextView tvPositionButton;
    @BindView(R.id.etInput)
    EditText etInput;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        setCancelable(false);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input, container, false);

        unbinder = ButterKnife.bind(this, view);

        etInput.setHint(getArguments().getInt(HINT));
        etInput.setInputType(getArguments().getInt(INPUT_TYPE));

        return view;
    }

    public void setNegativeButtonListener(View.OnClickListener listener) {
        tvNegativeButton.setOnClickListener(listener);
    }

    public void setPositiveButtonListener(View.OnClickListener listener) {
        tvPositionButton.setOnClickListener(listener);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();

        super.onDestroyView();
    }
}
