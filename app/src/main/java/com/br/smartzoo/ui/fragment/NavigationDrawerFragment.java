package com.br.smartzoo.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnDrawerOptionClick;
import com.br.smartzoo.model.interfaces.OnUpdateInformationListener;
import com.bumptech.glide.Glide;

/**
 * Created by adenilson on 05/05/16.
 */
public class NavigationDrawerFragment extends Fragment implements OnUpdateInformationListener {

    public static final String PREF_FILE_NAME = "testpref";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private OnDrawerOptionClick mOnDrawerOptionClick;
    private RelativeLayout mRelativeSelected;
    private LinearLayout mLinearSubOption;
    private boolean subOptionVisible = false;

    private TextView textViewCash;
    private TextView textViewVisitors;
    private TextView textViewReputation;
    private TextView textViewNumberVeterinaries;
    private TextView textViewNumberFeeders;
    private TextView textViewNumberJanitor;
    private TextView textViewNumberAnimals;
    private TextView textViewNumberCages;
    private ImageView imageViewReputation;
    private ImageView imageViewCash;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readToPreferences(getContext()
                , KEY_USER_LEARNED_DRAWER, "false"));

        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.navigation_drawer, container, false);

        bindRelativeHeader(view);
        bindHeader(view);
        bindOptionAnimals(view);
        bindOptionNews(view);
        bindOptionCages(view);
        bindOptionEmployees(view);
        bindOptionStock(view);
        bindOptionSubOptions(view);
        bindOptionBuy(view);
        bindOptionSettings(view);

        addNavigationDrawerContextToBusiness();

        return view;
    }

    private void addNavigationDrawerContextToBusiness() {
        ZooInfoBusiness.onUpdateHeader = this;
        onUpdate();
    }

    private void bindRelativeHeader(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_header);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDrawerOptionClick.onHeaderClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindHeader(View view) {

        textViewNumberCages = (TextView) view.findViewById(R.id.text_view_number_cages);

        textViewNumberAnimals = (TextView) view.findViewById(R.id.text_view_number_animals);

        textViewNumberJanitor = (TextView) view.findViewById(R.id.text_view_number_janitor);

        textViewNumberFeeders = (TextView) view.findViewById(R.id.text_view_number_feeders);

        textViewNumberVeterinaries = (TextView) view.findViewById(R.id
                .text_view_number_veterinaries);

        textViewReputation = (TextView) view.findViewById(R.id.text_view_reputation_value);

        textViewCash = (TextView) view.findViewById(R.id.text_view_cash_value);

        textViewVisitors = (TextView) view.findViewById(R.id.text_view_number_visitors);


        imageViewCash = (ImageView) view.findViewById(R.id.image_view_cash);
        Glide.with(getActivity()).load(R.drawable.ic_money).centerCrop().fitCenter().into
                (imageViewCash);

        imageViewReputation = (ImageView) view.findViewById(R.id.image_view_reputation);
        Glide.with(getActivity()).load(R.drawable.ic_reputation).fitCenter().into
                (imageViewReputation);

    }


    private void bindOptionStock(View view) {
        final RelativeLayout relativeOptionStock =
                (RelativeLayout) view.findViewById(R.id.relative_option_stock);
        relativeOptionStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionStock);
                mOnDrawerOptionClick.onStockClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionSubOptions(View view) {
        mLinearSubOption = (LinearLayout) view.findViewById(R.id.sub_options_buy);

        bindRelativeOptionBuyAnimal(view);
        bindRelativeOptionContractEmployee(view);
        bindRelativeOptionBuyfood(view);
        bindRelativeOptionBuildCage(view);


    }

    private void bindRelativeOptionBuildCage(View view) {
        final RelativeLayout relativeOptionBuildCage =
                (RelativeLayout) view.findViewById(R.id.relative_option_build_cage);
        relativeOptionBuildCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionBuildCage);
                mOnDrawerOptionClick.onBuildCageClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindRelativeOptionBuyfood(View view) {
        final RelativeLayout relativeOptionBuyfood =
                (RelativeLayout) view.findViewById(R.id.relative_option_buy_food);
        relativeOptionBuyfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionBuyfood);
                mOnDrawerOptionClick.onBuyFoodClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void changeOptionSelected(RelativeLayout relativeOption) {
        mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
        mRelativeSelected = relativeOption;
        relativeOption.setBackgroundColor(getResources()
                .getColor(R.color.yellow_300));
    }

    private void bindRelativeOptionContractEmployee(View view) {
        final RelativeLayout relativeOptionContractEmployee =
                (RelativeLayout) view.findViewById(R.id.relative_option_contract_employee);
        relativeOptionContractEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionContractEmployee);
                mOnDrawerOptionClick.onHireEmployeeClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindRelativeOptionBuyAnimal(View view) {
        final RelativeLayout relativeOptionBuyAnimal =
                (RelativeLayout) view.findViewById(R.id.relative_option_buy_animal);
        relativeOptionBuyAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionBuyAnimal);
                mOnDrawerOptionClick.onBuyAnimalClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionBuy(View view) {
        final RelativeLayout relativeOptionBuy =
                (RelativeLayout) view.findViewById(R.id.relative_option_buy);
        final ImageView icExpand = (ImageView) view.findViewById(R.id.ic_expand);

        relativeOptionBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subOptionVisible) {
                    subOptionVisible = true;
                    mLinearSubOption.setVisibility(View.VISIBLE);
                    icExpand.animate().rotation(180).setDuration(100);
                } else {
                    subOptionVisible = false;
                    mLinearSubOption.setVisibility(View.GONE);
                    icExpand.animate().rotation(0).setDuration(100);
                }
            }
        });


    }

    private void bindOptionSettings(View view) {
        final RelativeLayout relativeOptionSettings =
                (RelativeLayout) view.findViewById(R.id.relative_option_settings);
        relativeOptionSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionSettings);
                mOnDrawerOptionClick.onSettingsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionEmployees(View view) {
        final RelativeLayout relativeOptionEmployees =
                (RelativeLayout) view.findViewById(R.id.relative_option_employee);
        relativeOptionEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionEmployees);
                mOnDrawerOptionClick.onEmployeeClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionCages(View view) {
        final RelativeLayout relativeOptionCages =
                (RelativeLayout) view.findViewById(R.id.relative_option_cages);
        relativeOptionCages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionCages);
                mOnDrawerOptionClick.onCagesClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionNews(View view) {
        final RelativeLayout relativeOptionNews =
                (RelativeLayout) view.findViewById(R.id.relative_option_news);
        relativeOptionNews.setBackgroundColor(getResources().getColor(R.color.yellow_300));
        mRelativeSelected = relativeOptionNews;
        relativeOptionNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionNews);
                mOnDrawerOptionClick.onNewsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionAnimals(View view) {
        final RelativeLayout relativeOptionAnimals =
                (RelativeLayout) view.findViewById(R.id.relative_option_animals);

        relativeOptionAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOptionSelected(relativeOptionAnimals);
                mOnDrawerOptionClick.onAnimalsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,
                            mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                toolbar.setAlpha(1 - slideOffset / 2);

            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            //     mDrawerLayout.openDrawer(mContainerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName
            , String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME
                , context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readToPreferences(Context context, String preferenceName
            , String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME
                , context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }

    public void attachView(OnDrawerOptionClick onDrawerOptionClick) {
        this.mOnDrawerOptionClick = onDrawerOptionClick;
    }

    @Override
    public void onUpdate() {
        if (textViewNumberCages != null)
            textViewNumberCages.setText(getString(R.string.cages) + String.valueOf(ZooInfo.cages
                    .size()));

        int numberAnimals = 0;
        for (Cage cage : ZooInfo.cages) {
            numberAnimals += cage.getAnimals().size();
        }

        textViewNumberAnimals.setText(getString(R.string.animals) + String.valueOf(numberAnimals));


        int numberJanitors = 0;
        int numberFeeders = 0;
        int numberVets = 0;

        for (Employee employee : ZooInfo.employees) {
            if (employee instanceof Janitor)
                numberJanitors++;
            else if (employee instanceof Feeder)
                numberFeeders++;
            else
                numberVets++;
        }

        textViewNumberFeeders.setText(getString(R.string.feeders) + String.valueOf(numberFeeders));
        textViewNumberVeterinaries.setText(getString(R.string.veterinaries) + String.valueOf
                (numberVets));
        textViewNumberJanitor.setText(getString(R.string.janitors) + String.valueOf
                (numberJanitors));


        textViewReputation.setText(String.format("%.2f", ZooInfo.reputation));
        textViewCash.setText(String.format("%.2f", ZooInfo.money));

        textViewVisitors.setText(getString(R.string.visitors) + String.valueOf(ZooInfo.visitors.size()));
    }

}
