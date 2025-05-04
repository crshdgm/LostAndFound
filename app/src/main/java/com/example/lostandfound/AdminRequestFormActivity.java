package com.example.lostandfound;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminRequestFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Root ScrollView
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.setBackgroundResource(R.drawable.background);

        // Container LinearLayout
        LinearLayout root = new LinearLayout(this);
        ScrollView.LayoutParams rootParams = new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(rootParams);
        root.setOrientation(LinearLayout.VERTICAL);
        int padding = dp(16);
        root.setPadding(padding, padding, padding, padding);

        // 1. Header Bar
        LinearLayout header = new LinearLayout(this);
        header.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);

        TextView tvLostAnd = new TextView(this);
        tvLostAnd.setText("Lost & Found");
        tvLostAnd.setTextColor(Color.parseColor("#1A237E"));
        tvLostAnd.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvLostAnd.setTypeface(Typeface.DEFAULT_BOLD);
        header.addView(tvLostAnd);

        TextView tvFound = new TextView(this);
        LinearLayout.LayoutParams foundParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        foundParams.setMarginStart(dp(4));
        tvFound.setLayoutParams(foundParams);
        tvFound.setText("Welcome user!");
        tvFound.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvFound.setTypeface(Typeface.DEFAULT_BOLD);
        header.addView(tvFound);

        // Spacer
        View spacer = new View(this);
        LinearLayout.LayoutParams spacerLp = new LinearLayout.LayoutParams(
                0, 0, 1f);
        spacer.setLayoutParams(spacerLp);
        header.addView(spacer);

        ImageView ivProfile = new ImageView(this);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(
                dp(32), dp(32));
        ivProfile.setLayoutParams(ivParams);
        header.addView(ivProfile);

        root.addView(header);

        // 2. Search Bar
        LinearLayout searchBar = new LinearLayout(this);
        LinearLayout.LayoutParams searchLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(48));
        searchLp.setMargins(0, dp(16), 0, 0);
        searchBar.setLayoutParams(searchLp);
        searchBar.setOrientation(LinearLayout.HORIZONTAL);
        searchBar.setGravity(Gravity.CENTER_VERTICAL);
        searchBar.setPadding(dp(12), 0, dp(12), 0);

        EditText etSearch = new EditText(this);
        LinearLayout.LayoutParams etLp = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        etSearch.setLayoutParams(etLp);
        etSearch.setHint("Search Request");
        searchBar.addView(etSearch);

        ImageView ivSearch = new ImageView(this);
        LinearLayout.LayoutParams searchIconLp = new LinearLayout.LayoutParams(
                dp(24), dp(24));
        ivSearch.setLayoutParams(searchIconLp);
        searchBar.addView(ivSearch);

        root.addView(searchBar);

        // 3. Requests Section
        RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rlLp.setMargins(0, dp(24), 0, 0);
        rl.setLayoutParams(rlLp);

        LinearLayout containerCards = new LinearLayout(this);
        containerCards.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams cardsLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        containerCards.setLayoutParams(cardsLp);
        containerCards.setPadding(dp(8), 0, 0, 0);

        // Divider
        View divider = new View(this);
        LinearLayout.LayoutParams divLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(2));
        divLp.setMargins(0, dp(4), 0, 0);
        divider.setLayoutParams(divLp);
        divider.setBackgroundColor(Color.BLACK);
        containerCards.addView(divider);

        // Card entries
        containerCards.addView(createRequestCard(
                "Request Form 01", "2200000", "Juan Dela Cruz", "Tumbler", "04-05-2025"));
        containerCards.addView(createRequestCard(
                "Request Form 02", "2200000", "Juan Dela Cruz", "Wallet", "04-05-2025"));

        rl.addView(containerCards);
        root.addView(rl);

        // 4. Submit Button
        Button btnSubmit = new Button(this);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(48));
        btnLp.setMargins(0, dp(24), 0, 0);
        btnSubmit.setLayoutParams(btnLp);
        btnSubmit.setText("Submit");
        btnSubmit.setAllCaps(false);
        root.addView(btnSubmit);

        scrollView.addView(root);
        setContentView(scrollView);
    }

    private CardView createRequestCard(String title, String schoolId, String name, String item, String dateLost) {
        CardView card = new CardView(this);
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        cardLp.setMargins(0, dp(8), 0, dp(16));
        card.setLayoutParams(cardLp);
        card.setRadius(dp(8));
        card.setCardElevation(dp(4));

        LinearLayout inner = new LinearLayout(this);
        inner.setOrientation(LinearLayout.VERTICAL);
        inner.setPadding(dp(16), dp(16), dp(16), dp(16));

        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        inner.addView(tvTitle);

        TextView tvId = new TextView(this);
        tvId.setText("School ID: " + schoolId);
        tvId.setPadding(0, dp(8), 0, 0);
        inner.addView(tvId);

        TextView tvName = new TextView(this);
        tvName.setText("Name: " + name);
        inner.addView(tvName);

        TextView tvItem = new TextView(this);
        tvItem.setText("Item: " + item);
        inner.addView(tvItem);

        TextView tvDate = new TextView(this);
        tvDate.setText("Date Lost: " + dateLost);
        inner.addView(tvDate);

        TextView tvStatus = new TextView(this);
        tvStatus.setText("Status");
        LinearLayout.LayoutParams statusLp = new LinearLayout.LayoutParams(
                dp(136), ViewGroup.LayoutParams.WRAP_CONTENT);
        tvStatus.setLayoutParams(statusLp);
        inner.addView(tvStatus);

        card.addView(inner);
        return card;
    }

    private int dp(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
