package com.android.mihospital.loginandregister;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Estudio;
import com.android.mihospital.dominio.Receta;
import com.android.mihospital.ws.ServiceResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoriaClinicaActivity extends FragmentActivity {

	List<Estudio> estudios;
	List<Receta> medicamentos;
	EstudioAdapter estudioAdapter;
	public EstudioAdapter getEstudioAdapter() {
		return estudioAdapter;
	}

	public void setEstudioAdapter(EstudioAdapter estudioAdapter) {
		this.estudioAdapter = estudioAdapter;
	}

	public RecetaAdapter getRecetaAdapter() {
		return recetaAdapter;
	}

	public void setRecetaAdapter(RecetaAdapter recetaAdapter) {
		this.recetaAdapter = recetaAdapter;
	}
	RecetaAdapter recetaAdapter;
	Context context;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historia_clinica);
		context = this;
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(),context);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		estudios = new ArrayList<Estudio>();
		medicamentos = new ArrayList<Receta>();

		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Buscando datos");
		progressDialog.show();
		
		new ListaTask(getIntent().getIntExtra("idPaciente", -1)).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historia_clinica, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		Context context;
		public SectionsPagerAdapter(FragmentManager fm,Context context) {
			super(fm);
			this.context = context;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment(this.context);
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);

			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	@SuppressLint("ValidFragment")
	public class DummySectionFragment extends Fragment {
		Context context;
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		@SuppressLint("ValidFragment")
		public DummySectionFragment(Context context) {
			this.context=context;
				
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_historia_clinica_dummy, container, false);

			int section = getArguments().getInt(ARG_SECTION_NUMBER);
			
			ListView listView = (ListView) rootView
					.findViewById(R.id.listTurno2);

			if (section == 0)
				agregarPaginaEstudios(rootView, listView);
			else
				agregarPaginaRecetas(rootView, listView);

			return rootView;
		}

		private void agregarPaginaRecetas(View rootView, ListView listView) {
			RecetaAdapter recetaAdapter=new RecetaAdapter(rootView.getContext(),
					android.R.layout.simple_list_item_1, medicamentos);
			
			listView.setAdapter(recetaAdapter);
			
			((HistoriaClinicaActivity)context).setRecetaAdapter(recetaAdapter);
			
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(getApplicationContext(),
							DetalleRecetaActivity.class);
					intent.putExtra("idReceta", medicamentos.get(position)
							.getId());
					startActivity(intent);
				}
			});
		}

		private void agregarPaginaEstudios(View rootView, ListView listView) {
			EstudioAdapter estudioAdapter=new EstudioAdapter(rootView.getContext(),
					android.R.layout.simple_list_item_1, estudios);
			
			
			listView.setAdapter(estudioAdapter);
			
			((HistoriaClinicaActivity)context).setEstudioAdapter(estudioAdapter);
			
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(getApplicationContext(),
							DetalleEstudioMedicoActivity.class);
					intent.putExtra("idEstudio", estudios.get(position).getId());
					startActivity(intent);
				}
			});
		}

	}
	public class ListaTask extends AsyncTask<Void, Void, Boolean> {

		Integer id;
		String contentEstudios;
		String contentMedicamentos;
		private ServiceResult result2;
		private ServiceResult result;
		public ListaTask(Integer idPaciente) {
			super();
			this.id = idPaciente;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			result = ServiceProvider.getClient().obtenerEstudios(id);
			contentEstudios = result.getMensaje();
			
			result2 = ServiceProvider.getClient().obtenerRecetas(id);
			contentMedicamentos = result2.getMensaje();
			
				
			return (!result.getResultado().equals("ERROR") && !result2.getResultado().equals("ERROR"));

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			
			progressDialog.dismiss();			
			
			if (!success){
				Toast.makeText(context.getApplicationContext(),
						"No se pudo obtener los datos", Toast.LENGTH_SHORT)
						.show();
			}else
			{
				Gson gson = new Gson();
				if(result.getResultado().equals("OK")){
					Type listType = new TypeToken<List<Estudio>>() {
					}.getType();
					List<Estudio> list2 = gson.fromJson(contentEstudios, listType);
					estudios.addAll(list2);
					estudioAdapter.notifyDataSetChanged();
					
				}
				if(result2.getResultado().equals("OK")){
					Type listType = new TypeToken<List<Receta>>() {
					}.getType();
					List<Receta> list = gson.fromJson(contentMedicamentos, listType);
					medicamentos.addAll(list);
					recetaAdapter.notifyDataSetChanged();
				}
			}
		}

	}

}
