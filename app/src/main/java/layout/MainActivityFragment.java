package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.rawr.dropshop.R;
import com.rawr.dropshop.adapters.ViewPagerAdapter;
import com.rawr.dropshop.fragments.Inventarios;
import com.rawr.dropshop.fragments.Pedidos;
import com.rawr.dropshop.fragments.Ventas;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tabLayout_main)
    TabLayout tabLayoutMain;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;
    @BindView(R.id.fab_orden)
    FloatingActionButton fabOrden;
    @BindView(R.id.fab_stock)
    FloatingActionButton fabStock;
    @BindView(R.id.fab_ventas)
    FloatingActionButton fabVentas;
    @BindView(R.id.fab_plus)
    FloatingActionButton fabPlus;
    @BindView(R.id.fab_ventas_text)
    TextView fabVentasText;
    @BindView(R.id.fab_in_stock_text)
    TextView fabInStockText;
    @BindView(R.id.fab_orden_text)
    TextView fabOrdenText;


    Animation fabOpen, fabClose, fabClockWise, fabAntiClockWise;
    boolean isOpen = false;
    ViewPagerAdapter viewPagerAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainActivityFragment newInstance(String param1, String param2) {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        ButterKnife.bind(this, view);

        configureElements(view);
        llamarListenersMain();
        return view;
    }

    private void configureElements(View view) {

        fabPlus = (FloatingActionButton) view.findViewById(R.id.fab_plus);
        fabOrden = (FloatingActionButton) view.findViewById(R.id.fab_orden);
        fabStock = (FloatingActionButton) view.findViewById(R.id.fab_stock);
        fabVentas = (FloatingActionButton) view.findViewById(R.id.fab_ventas);
        fabOrdenText = (TextView) view.findViewById(R.id.fab_orden_text);
        fabInStockText = (TextView) view.findViewById(R.id.fab_in_stock_text);
        fabVentasText = (TextView) view.findViewById(R.id.fab_ventas_text);
        fabOpen = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.rotate_anticlockwise);

        tabLayoutMain = (TabLayout) view.findViewById(R.id.tabLayout_main);
        viewPagerMain = (ViewPager) view.findViewById(R.id.view_pager_main);
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Pedidos(), getResources().getString(R.string.pedidos));
        viewPagerAdapter.addFragments(new Inventarios(), getResources().getString(R.string.inventarios));
        viewPagerAdapter.addFragments(new Ventas(), getResources().getString(R.string.ventas));
        viewPagerMain.setAdapter(viewPagerAdapter);
        tabLayoutMain.setupWithViewPager(viewPagerMain);

        /*************/
        fabOpen = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.rotate_anticlockwise);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Pedidos(), getResources().getString(R.string.pedidos));
        viewPagerAdapter.addFragments(new Inventarios(), getResources().getString(R.string.inventarios));
        viewPagerAdapter.addFragments(new Ventas(), getResources().getString(R.string.ventas));
        viewPagerMain.setAdapter(viewPagerAdapter);
    }

    public void llamarListenersMain() {


        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    fabVentas.startAnimation(fabClose);
                    fabVentas.setClickable(false);
                    fabVentasText.startAnimation(fabClose);
                    fabVentasText.setVisibility(View.GONE);
                    fabStock.startAnimation(fabClose);
                    fabStock.setClickable(false);
                    fabInStockText.startAnimation(fabClose);
                    fabInStockText.setVisibility(View.GONE);
                    fabOrden.startAnimation(fabClose);
                    fabOrden.setClickable(false);
                    fabOrdenText.startAnimation(fabClose);
                    fabOrdenText.setVisibility(View.GONE);
                    fabPlus.startAnimation(fabAntiClockWise);
                    isOpen = false;
                } else {
                    fabVentas.startAnimation(fabOpen);
                    fabVentas.setClickable(true);
                    fabVentasText.startAnimation(fabOpen);
                    fabStock.startAnimation(fabOpen);
                    fabStock.setClickable(true);
                    fabInStockText.startAnimation(fabOpen);
                    fabOrden.startAnimation(fabOpen);
                    fabOrden.setClickable(true);
                    fabOrdenText.startAnimation(fabOpen);
                    fabPlus.startAnimation(fabClockWise);
                    isOpen = true;
                }
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }/*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
