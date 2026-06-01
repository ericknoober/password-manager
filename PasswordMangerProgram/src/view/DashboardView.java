package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

/*
 * Description: This is the dashboard view class where all the 
 * statistics are initalized like the chart, labels, headers, and panels
 * 
 * Some lines about the chart are from the climate data project
 */

public class DashboardView extends JFrame {

	// user menu
	userMenuBar userMenu;

	// JPanel
	JPanel statsPanel;
	JPanel chartPanel;
	JPanel passwordHealthPanel;

	// JLabel
	JLabel topSitesLabel;
	public JLabel topSite;
	JLabel tagsCountLabel;
	public JLabel[] tagsCount;
	public DefaultPieDataset dataset;

	// password health
	JLabel passwordHealthHeader;
	public JTextArea passwordHealthLabel;
	JLabel passwordHealthCount;

	public DashboardView() {

		// creates user menu
		userMenu = new userMenuBar();
		add(userMenu);

		displayOldestPasswordHealth();
		statsPanelSetup();
		displayNumberStats();
		frameSetup();
	}

	// sets up frame
	public void frameSetup() {

		setTitle("KyLocks - Breach Scanner");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setFocusable(true);

	}

	// sets up the stats panel
	public void statsPanelSetup() {

		statsPanel = new JPanel();
		statsPanel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		statsPanel.setLayout(null);
		statsPanel.setBounds(75, 100, 800, 400);
		add(statsPanel);

	}

	// sets up the chart panel
	public void chartSetup(int start, int end) {

		// ring chart
		dataset = new DefaultPieDataset();
		JFreeChart chart = ChartFactory.createRingChart("My Passwords", dataset, false, false, false);

		// paint component used to draw over the chart
		// chart panel
		ChartPanel chartPanel = new ChartPanel(chart) {
			// draws inside the panel
			@Override
			public void paintComponent(Graphics g) {
				// draws text
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(WebsiteFont.LabelFont.getWebsiteFont());
				g2.setColor(WebsiteColours.DARKRED.getWebsiteColor());
				String text = start + "/" + end;
				g2.drawString(text, 170, 140);
			}
		};

		// chart panel
		chart.setBackgroundPaint(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		chartPanel.setBounds(-75, 50, 400, 250);
		chartPanel.setBorder(BorderFactory.createEmptyBorder());
		chartPanel.setVisible(true);

		// features to remove the background borders
		RingPlot plot = (RingPlot) chart.getPlot();
		plot.setShadowPaint(null);
		plot.setBackgroundPaint(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		plot.setLabelGenerator(null);
		plot.setOutlineVisible(false);
		statsPanel.add(chartPanel);

	}

	// displays the dashboard stats
	public void displayNumberStats() {

		// top sites label
		topSitesLabel = new JLabel();
		topSitesLabel.setBounds(350, 25, 400, 25);
		topSitesLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		topSitesLabel.setText("Your top entry site is: ");
		statsPanel.add(topSitesLabel);

		// top site label
		topSite = new JLabel();
		topSite.setBounds(360, 50, 500, 75);
		topSite.setFont(WebsiteFont.LabelFont.getDynamicFont(75));
		statsPanel.add(topSite);

		// tags
		tagsCountLabel = new JLabel();
		tagsCountLabel.setBounds(350, 175, 400, 25);
		tagsCountLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		tagsCountLabel.setText("Tags Count: ");
		statsPanel.add(tagsCountLabel);

		// displays the three tags
		tagsCount = new JLabel[3];
		for (int i = 0; i < tagsCount.length; i++) {

			// tag count
			tagsCount[i] = new JLabel("test");
			tagsCount[i].setBounds(360, 200 + (i * 50), 400, 60);
			tagsCount[i].setFont(WebsiteFont.LabelFont.getDynamicFont(50));
			statsPanel.add(tagsCount[i]);

		}

	}

	// display the oldest password
	public void displayOldestPasswordHealth() {

		// password health panel
		passwordHealthPanel = new JPanel();
		passwordHealthPanel.setBackground(WebsiteColours.BRIGHTORANGE.getWebsiteColor());
		passwordHealthPanel.setLayout(null);
		passwordHealthPanel.setBounds(900, 100, 250, 400);
		add(passwordHealthPanel);

		// password health header
		// title
		passwordHealthHeader = new JLabel();
		passwordHealthHeader.setBounds(25, 25, 400, 25);
		passwordHealthHeader.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		passwordHealthHeader.setText("Your Oldest Entry is: ");
		passwordHealthPanel.add(passwordHealthHeader);

		// password health label
		// displays the information of the entry
		passwordHealthLabel = new JTextArea();
		passwordHealthLabel.setBounds(25, 75, 400, 400);
		passwordHealthLabel.setFont(WebsiteFont.LabelFont.getWebsiteFont());
		passwordHealthLabel.setBackground(null);
		passwordHealthLabel.setEditable(false);
		passwordHealthLabel.setLineWrap(true);
		passwordHealthLabel.setWrapStyleWord(true);
		passwordHealthPanel.add(passwordHealthLabel);

	}

}
