package lcom;

@Controller
@RequestMapping("/tos")
public class TermsOfServiceController extends Support {

	private TermsOfServiceService termsOfServiceService;

	@Autowired
	public void setTermsOfServiceService(TermsOfServiceService termsOfServiceService) {
		this.termsOfServiceService = termsOfServiceService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TermsOfServiceDTO getLatestActiveTermsOfService() {
		return TermsOfServiceDTO.fromTermsOfService(termsOfServiceService.getLatestActiveTermsOfService());
	}
}