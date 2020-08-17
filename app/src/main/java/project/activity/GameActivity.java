package project.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.heydari.dotsandboxes.R;

import project.view.GameBoardView;
import project.app.EnhancedActivity;
import project.dialog.OptionDialog;


public class GameActivity extends EnhancedActivity {

  private GameBoardView gameBoardView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    final boolean isMultiplayer = getIntent().getExtras().getBoolean("isMultiplayer");
    final boolean mustResume = getIntent().getExtras().getBoolean("resume");

    ImageButton btn_reset = (ImageButton) findViewById(R.id.btn_reset);
    ImageButton btn_option = (ImageButton) findViewById(R.id.btn_option);
    gameBoardView = (GameBoardView) findViewById(R.id.gameview);

    btn_reset.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        gameBoardView.resetGame();
      }
    });

    btn_option.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        gameBoardView.startGame(isMultiplayer);
        Dialog dialog = new OptionDialog(GameActivity.this);
        dialog.show();
      }
    });

    if (mustResume) {
      gameBoardView.loadGame();
    } else {
      gameBoardView.startGame(isMultiplayer);
    }
  }


  public GameBoardView getGameBoardView() {
    return gameBoardView;
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameBoardView.saveGame();
  }
}
