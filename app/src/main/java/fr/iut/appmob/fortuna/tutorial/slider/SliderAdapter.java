package fr.iut.appmob.fortuna.tutorial.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import fr.iut.appmob.fortuna.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {

            R.drawable.logo_fortuna,
            R.drawable.fleche_button,
            R.drawable.fleche_oui,
            R.drawable.fleche_dls,
            R.drawable.fleche_nav_home,
            R.drawable.fleche_stat,
            R.drawable.fleche_setting,
            R.drawable.fleche_schedule,

    };

    int titles[] = {
            R.string.first_slide_tuto_txt,
            R.string.second_slide_tuto_txt,
            R.string.third_slide_tuto_txt,
            R.string.fourth_slide_tuto_txt,
            R.string.fifth_slide_tuto_txt,
            R.string.sixth_slide_tuto_txt,
            R.string.seventh_slide_tuto_txt,
            R.string.eighth_slide_tuto_txt,
    };


    @Override
    public int getCount() {
        return titles.length;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.first_slide_on_boarding_screen, container,false);

        ImageView imageView = view.findViewById(R.id.first_slide_img_on_boarding_screen);
        TextView heading = view.findViewById(R.id.first_slide_on_boarding_screen_textView);

        imageView.setImageResource(images[position]);
        heading.setText(titles[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
