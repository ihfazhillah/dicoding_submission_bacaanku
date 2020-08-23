package com.ihfazh.bacaanku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ihfazh.bacaanku.data.Book;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private List<Book> books;
    private OnItemClickCallback onItemClickCallback;

    public BookListAdapter(List<Book> list){
        this.books = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        final Book book = books.get(position);

        holder.tvAuthor.setText(book.getAuthor());
        holder.tvDescription.setText(book.getDescription());
        holder.tvTitle.setText(book.getTitle());

        Glide.with(holder.itemView)
                .load(book.getImageUrl())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(books.get(holder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription, tvAuthor;
        private ImageView image;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            image = itemView.findViewById(R.id.img_book);
        }
    }
}
