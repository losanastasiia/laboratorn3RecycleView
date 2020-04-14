package example.my.laboratorn3recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Созданием список картин
        List<Picture> pictures = new ArrayList<>();
        //Заполняем список картинами, создавая навый элемент и заполняя его с помощью конструктора, а потом добавляя в список
        pictures.add(new Picture("Иван Айвазовский",
                "Девятый вал",
                "«Девятый вал» — одна из самых знаменитых картин российского художника-мариниста армянского происхождения Ивана Айвазовского, хранится в Русском музее в Санкт-Петербурге. Написана в 1850 году. Живописец изображает море после очень сильного ночного шторма и людей, потерпевших кораблекрушение.",
                R.drawable.picture1));
        pictures.add(new Picture("Сальвадор Дали",
                "Постоянство памяти",
                "«Постоянство памяти» — одна из самых известных картин художника Сальвадора Дали. Находится в Музее современного искусства в Нью-Йорке с 1934 года. Известна также как «Мягкие часы», «Твердость памяти» или «Стойкость памяти» или «Течение времени» или «Время».",
                R.drawable.picture2));
        pictures.add(new Picture("Исаак Левитан",
                "Золотая осень",
                "«Золотая осень» — пейзаж русского художника Исаака Левитана, написанный в 1895 году. Принадлежит Государственной Третьяковской галерее. Размер картины — 82 × 126 см. Левитан начал работу над картиной осенью 1895 года, когда он жил в усадьбе Горка в Тверской губернии; там же были написаны первые этюды.",
                R.drawable.picture3));
        pictures.add(new Picture("Иван Шишкин",
                "Утро в сосновом лесу",
                "«Утро в сосновом лесу» — картина русских художников И. И. Шишкина и К. А. Савицкого. Савицкий написал медведей, но коллекционер П. М. Третьяков стёр его подпись, так что автором картины часто указывается один Шишкин.",
                R.drawable.picture4));

        RecyclerView rv = findViewById(R.id.list);
        //мы будем использовать LinearLayoutManager. он обеспечивает вид  RecyclerView аналогично ListView.
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);

        //Добавляем адаптер к списку
        rv.setAdapter(new MyAdapter(pictures));

    }


    //Создаем адаптер
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.PictureViewHolder>  {

        private List<Picture> list;

        public MyAdapter(List<Picture> list) {
            this.list = list;
        }


        //creating class ViewHolder
        public class PictureViewHolder extends RecyclerView.ViewHolder {

            public TextView author;
            public TextView title;
            public ImageView image;
            public Button button;

            //конструктор
            public PictureViewHolder(@NonNull View itemView) {
                super(itemView);
                author = itemView.findViewById(R.id.author);
                title = itemView.findViewById(R.id.title);
                image = itemView.findViewById(R.id.imageView);
                button = itemView.findViewById(R.id.button_card);

            }
        }

        // этот метод вызывается, когда наш ViewHolder должен быть инициализирован. Мы указываем макет для каждого элемента RecyclerView.
        //  Затем LayoutInflater заполняет макет, и передает его в конструктор ViewHolder.
        @NonNull
        @Override
        public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

            PictureViewHolder holder = new PictureViewHolder(v);

            return holder;
        }

        //здесь нужно установить значения полей элемента списка
        @Override
        public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
            holder.author.setText(list.get(position).getAuthor());
            holder.title.setText("\""+list.get(position).getTitle()+"\"");
            holder.image.setImageResource(list.get(position).getImageId());
            //Кнопка для просмотра дополнительной информации об элементе списка
            holder.button.setOnClickListener(new View.OnClickListener()
            {
                //При нажатии на кнопку вызывается метод главной активити, который вызывает вторую активити с дополнительной информацией о картине
                @Override
                public void onClick(View v) {
                    //Создаем новое намерение
                    Intent intent = new Intent(MainActivity.this, PictureActivity.class);
                    //передаем данные второй активити (PictureActivity)
                    intent.putExtra("author", (list.get(position)).getAuthor());
                    intent.putExtra("title", (list.get(position)).getTitle());
                    intent.putExtra("information", (list.get(position)).getInformation());
                    intent.putExtra("image", (list.get(position)).getImageId());

                    startActivity(intent); //запускаем PictureActivity
                }
            });

        }

        //метод getItemCount вернет количество элементов, присутствующих в данных. Так как наши данные в виде списка, мы просто вызываем метод size у объекта списка:
        @Override
        public int getItemCount() {
            return list.size();
        }
    }



    //Класс элемента списка - картины
    class Picture {
        private String author;
        private String title;
        private String information;
        private int imageId;

        //конструктор
        public Picture(String author,String title, String information, int imageId) {
            this.author = author;
            this.title = title;
            this.information = information;
            this.imageId = imageId;
        }

        //геттеры и сеттеры
        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }
}
