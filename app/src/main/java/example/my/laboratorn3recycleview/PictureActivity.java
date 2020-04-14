package example.my.laboratorn3recycleview;

        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

//Вторая активити, отображает страницу с информацией  картине
public class PictureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture);

        //Получить ссылки на TextView и ImageView, куа мы будем записывать данные
        TextView title = findViewById(R.id.titlePicture);
        ImageView imageView = findViewById(R.id.imagePicture);
        TextView description = findViewById(R.id.informationPicture);

        //Получить данные из предыдущей активити и записать в текущую
        Bundle bundle = getIntent().getExtras();
        title.setText(getIntent().getStringExtra( "author")+" \""+getIntent().getStringExtra("title")+"\"");
        imageView.setImageResource(getIntent().getIntExtra("image", 0));
        description.setText(getIntent().getStringExtra("information"));

        // Кнопка, чтобы вернуться к списку
        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
